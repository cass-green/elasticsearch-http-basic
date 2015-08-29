
package com.asquera.elasticsearch.plugins.http.auth.integration;


import java.net.InetSocketAddress;

import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.Base64;
import org.elasticsearch.http.HttpServerTransport;
import org.elasticsearch.test.ElasticsearchIntegrationTest;
import org.elasticsearch.test.rest.client.http.HttpRequestBuilder;
import com.asquera.elasticsearch.plugins.http.HttpBasicServerPlugin;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.ImmutableSettings;

/**
 *
 * @author Ernesto Miguez (ernesto.miguez@asquera.de)
 */

public class HttpBasicServerPluginIntegrationTest extends
ElasticsearchIntegrationTest {

  protected final String localhost = "127.0.0.1";


  /**
   *
   * @return a Builder with the plugin included and bind_host and publish_host
   * set to localhost, from where the client's request ip will be done.
   */
  protected Builder builderWithPlugin() {
    return ImmutableSettings.settingsBuilder()
      .put("network.host", localhost)
      .put("plugin.types", HttpBasicServerPlugin.class.getName());
  }

  protected HttpRequestBuilder requestWithCredentials(String credentials) throws Exception {
        return httpClient().path("/_status")
          .addHeader("Authorization", "Basic " + Base64.encodeBytes(credentials.getBytes()));
    }

}
