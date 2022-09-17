package rs.advig.rest.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Item;
import rs.advig.rest.model.Labels;
import rs.advig.rest.model.Payment;
import rs.advig.rest.model.Request;
import rs.advig.rest.model.Response;
import rs.advig.rest.repository.CertRepository;
import rs.advig.rest.repository.RequestRepository;
import rs.advig.rest.repository.ResponseRepository;
import rs.advig.rest.service.ResponseService;
import rs.advig.rest.util.ConnectionManager;

@Service
public class ResponseServiceImpl implements ResponseService {
	
	private ResponseRepository responseRepository;
	private CertRepository certRepository;
	private RequestRepository requestRepository;
	
	public ResponseServiceImpl(ResponseRepository responseRepository, CertRepository certRepository, RequestRepository requestRepository) {
		super();
		this.responseRepository = responseRepository;
		this.certRepository = certRepository;
		this.requestRepository = requestRepository;
	}

	@Override
	public Response saveResponse(Response response, String authId, Request request) {
		response.setAuthId(authId);
		response.setRequestId(request);
		return responseRepository.save(response);
	}

	@Override
	public List<Response> getAllResponses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Response> getAllResponsesByAuthId(String authId) {
		return responseRepository.findAllResponsesByAuthId(authId);
	}

	@Override
	public Response getResponseById(long id) {
		return responseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Response", "id", id));
	}

	@Override
	public Response getResponseByRequestIdValue(String requestIdvalue) {
		return responseRepository.findResponseByRequestIdValue(requestIdvalue);
	}

	@Override
	public Response updateResponse(Response response, String requestIdValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateResponse(Response response, long requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response contactTaxAuth(Request request) {

		JSONObject sslParams = new JSONObject();
		sslParams.put("invoiceNumber", "1102/1.0");
		if(StringUtils.isNotBlank(request.getDateAndTimeOfIssue()))
			sslParams.put("dateAndTimeOfIssue", request.getDateAndTimeOfIssue());
		if(StringUtils.isNotBlank(request.getCashier()))
			sslParams.put("cashier", request.getCashier());
		if(StringUtils.isNotBlank(request.getBuyerId()))
			sslParams.put("buyerId", request.getBuyerId());
		if(StringUtils.isNotBlank(request.getReferentDocumentDT()))
			sslParams.put("referentDocumentDT",request.getReferentDocumentDT());
		if(StringUtils.isNotBlank(request.getReferentDocumentNumber()))
			sslParams.put("referentDocumentNumber", request.getReferentDocumentNumber());
		sslParams.put("invoiceType", request.getInvoiceType());
		sslParams.put("transactionType", request.getTransactionType());
		
		JSONArray sslItems = new JSONArray();
		Double zbirIznosaStavki = 0.00;
		
		for(Item item : request.getItems()) {
			String barcode = "";
			if(StringUtils.isNotBlank(item.getGtin()))
				barcode = item.getGtin() + " ";			
			
			JSONObject sslItem = new JSONObject();
			sslItem.put("gtin", item.getGtin());
			sslItem.put("name", barcode + item.getName() + "/" + item.getUnitLabel());
			sslItem.put("quantity", item.getQuantity());
			sslItem.put("unitPrice", item.getUnitPrice());
			sslItem.put("totalAmount", item.getTotalAmount());
			
			JSONArray itemLabels = new JSONArray();
			for(Labels label : item.getLabels()) {
				itemLabels.put(label.getLabel());
			}
			sslItem.put("labels", itemLabels);
			zbirIznosaStavki += Math.round(item.getQuantity() * item.getUnitPrice() * 100.0) / 100.0;
			sslItems.put(sslItem);
		}
		sslParams.put("items", sslItems);
		
		JSONArray sslPayments = new JSONArray();
		Double zbirIznosaPlacanja = 0.00;
		
		for(Payment payment : request.getPayment()) {
			JSONObject sslPayment = new JSONObject();
			sslPayment.put("paymentType", payment.getPaymentType());
			sslPayment.put("amount", payment.getAmount());
			zbirIznosaPlacanja += Math.round(payment.getAmount() * 100.0) / 100.0;
			sslPayments.put(sslPayment);
		}
		
		sslParams.put("payment", sslPayments);
		
		try {
			ConnectionManager cm = new ConnectionManager(this.certRepository);
			HttpsURLConnection con = cm.getHttpsConnection("https://vsdc.sandbox.suf.purs.gov.rs/api/v3/invoices", request.getAuthId());
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			con.setRequestProperty("PAC", certRepository.findCertByAuthId(request.getAuthId()).getPac());
			con.setRequestProperty("RequestId", request.getRequestId().replace("-", ""));
			con.setRequestProperty("Accept-Language", "sr-Cyrl-RS");
			con.setDoOutput(true);
			con.setDoInput(true);

			OutputStream os = con.getOutputStream();
			os.write(sslParams.toString().getBytes("utf-8"));
			os.flush();
			os.close();
			
			if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				System.out.println(response);
				return null;
				//return jakarta.ws.rs.core.Response.status(con.getResponseCode()).entity(con.getResponseMessage()).build();
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();
			con.disconnect();

			ObjectMapper objectMapper = new ObjectMapper();
			Response r = objectMapper.readValue(response.toString(), Response.class);
			r.setRequestId(request);
			r.setAuthId(request.getAuthId());
			r.setRequestIdValue(request.getRequestId());
			
			String[] journal = r.getJournal().split("\r\n");
			List<String> resultList = new ArrayList<String>();
			boolean ukupanIznosProsao = false;
			DecimalFormat dfGerman = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.GERMAN));

			for (String linija : journal) {
				
				System.out.println(linija);
				
				if (linija.contains("Укупан износ:")) {
					ukupanIznosProsao = true;
				}
				if (ukupanIznosProsao && linija.contains("===")) {
					String left = String.format("%-20s", "Повраћај:");
					String right = String.format("%20s",
							dfGerman.format(zbirIznosaPlacanja - zbirIznosaStavki));
					resultList.add(left + right);
					ukupanIznosProsao = false;
				}
				resultList.add(linija);

				if (linija.contains("Укупан износ:") && r.getRequestId().getPrepaidAmount() != 0
						&& r.getRequestId().getPrepaidTax() != 0
						&& r.getRequestId().getPayableAmount() != 0) {
					dfGerman = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.GERMAN));
					
					String left = String.format("%-20s","Плаћено авансом:");
					String right = String.format("%20s", dfGerman.format(r.getRequestId().getPrepaidAmount()));
					resultList.add(left + right);
					left = String.format("%-20s","ПДВ на аванс:");
					right = String.format("%20s",  dfGerman.format(r.getRequestId().getPrepaidTax()));
					resultList.add(left + right);
					left = String.format("%-22s","Преостало за плаћање:");
					right = String.format("%18s",  dfGerman.format(r.getRequestId().getPayableAmount()));
					resultList.add(left + right);
				}
			}
			
			r.setJournal(String.join("\r\n", resultList));
			r = responseRepository.save(r);
			return r;
						
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
