package rs.advig.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import rs.advig.rest.repository.CertRepository;

public class ConnectionManager {
	
	private CertRepository cRepository;
		
	public ConnectionManager(CertRepository cRepository) {
		super();
		this.cRepository = cRepository;
	}
	
	public HttpsURLConnection getHttpsConnection(String urlString, String authId) {
		
		URL url;
		try {
			url = new URL(urlString);
			ConnectionManager cm = new ConnectionManager(cRepository);
			KeyManager[] kms = cm.getKeyManagerArray(authId);			
			TrustManager[] trustAllCerts = cm.trustAllCertsTrustManager();
			HostnameVerifier allHostsValid = cm.validateAllHosts();
			
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(kms, trustAllCerts, new SecureRandom());
			SSLContext.setDefault(sslContext);

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			
			con.setRequestProperty("Accept", "application/json");
			con.setConnectTimeout(10000);
			con.setSSLSocketFactory(sslContext.getSocketFactory());
			
			
			return con;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
public HttpsURLConnection getHttpsConnection(String urlString) {
		
		URL url;
		try {
			url = new URL(urlString);
			ConnectionManager cm = new ConnectionManager(cRepository);
		
			TrustManager[] trustAllCerts = cm.trustAllCertsTrustManager();
			HostnameVerifier allHostsValid = cm.validateAllHosts();
			
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new SecureRandom());
			SSLContext.setDefault(sslContext);

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestProperty("Accept-Language", "sr-Cyrl-RS");
			con.setRequestProperty("Accept", "application/json");
			con.setConnectTimeout(10000);
			con.setSSLSocketFactory(sslContext.getSocketFactory());
			
			
			return con;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	
	public KeyManager [] getKeyManagerArray(String authId) {
		KeyStore clientStore;
		try {
			clientStore = KeyStore.getInstance("PKCS12");
			File certFile = new File("data/cert.pfx");   //dev
			
//			File certFile = new File(System.getProperty("catalina.home") + "/data/" + authId + "/" + cRepository.findCertByAuthId(authId).getNaziv()); //prod
						
			clientStore.load(new FileInputStream(certFile), cRepository.findCertByAuthId(authId).getSifra().toCharArray());
			
			KeyManagerFactory kmf = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, cRepository.findCertByAuthId(authId).getSifra().toCharArray());
			
			return kmf.getKeyManagers();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
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
