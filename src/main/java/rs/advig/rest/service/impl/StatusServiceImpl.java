package rs.advig.rest.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Service;

import rs.advig.rest.repository.CertRepository;
import rs.advig.rest.service.StatusService;
import rs.advig.rest.util.ConnectionManager;

@Service
public class StatusServiceImpl implements StatusService {
	
	private CertRepository certRepository;
	
	public StatusServiceImpl(CertRepository certRepository) {
		super();
		this.certRepository = certRepository;
	}
	
	@Override
	public int status(String authId) {
		
		try {
			ConnectionManager cm = new ConnectionManager(this.certRepository);
			HttpsURLConnection con = cm.getHttpsConnection("https://vsdc.sandbox.suf.purs.gov.rs/api/v3/attention", authId);
			con.setRequestMethod("GET");
			
			con.setDoInput(true);
			return con.getResponseCode();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 404;
	}
	
	
	
	public TrustManager[] trustAllCertsTrustManager() {
		return new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}
		} };
	}
	
	public HostnameVerifier validateAllHosts() {
		return new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
	}

}
