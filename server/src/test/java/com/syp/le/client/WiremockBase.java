package com.syp.le.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 27, 2019
 */
public abstract class WiremockBase {

	protected void createWireMockStub(String urlRegex, String fileName) {
		stubFor(get(urlPathMatching(urlRegex)).willReturn(aResponse().withStatus(200).withBodyFile(fileName)));
	}
}
