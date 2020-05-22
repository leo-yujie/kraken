package com.kraken.security.authentication.container;

import com.kraken.config.security.client.api.SecurityClientProperties;
import com.kraken.config.security.container.api.SecurityContainerProperties;
import com.kraken.security.client.api.SecurityClient;
import com.kraken.security.decoder.api.TokenDecoder;
import com.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.kraken.security.authentication.api.AuthenticationMode.CONTAINER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ContainerUserProviderFactoryTest {

  @Mock
  SecurityClientProperties clientProperties;
  @Mock
  SecurityContainerProperties containerProperties;
  @Mock
  TokenDecoder decoder;
  @Mock
  SecurityClient client;

  ContainerUserProviderFactory factory;

  @BeforeEach
  public void setUp() throws IOException{
    factory = new ContainerUserProviderFactory(clientProperties, containerProperties, decoder, client);
  }

  @Test
  public void shouldCreate() {
    final var userProvider = factory.create("userId");
    assertThat(userProvider).isNotNull();
  }

  @Test
  public void shouldGetMode() {
    assertThat(factory.getMode()).isEqualTo(CONTAINER);
  }

  @Test
  public void shouldPassNPE() {
    TestUtils.shouldPassNPE(ContainerUserProviderFactory.class);
  }

}