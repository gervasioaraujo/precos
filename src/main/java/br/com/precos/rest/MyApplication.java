package br.com.precos.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class MyApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		// Suporte ao file upload.
		singletons.add(new MultiPartFeature());
		return singletons;
	}

	
	// método que configura a propriedade jersey.config.serrver.provider.pachages que indica
	// ao Jersey em qual pacote estão as classes dos web services
	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotações REST.
		properties.put("jersey.config.server.provider.packages", "br.com.precos");
		return properties;
	}
}
