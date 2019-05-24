package com.kraken.commons.storage.client;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
class StorageClientProperties {

  @NonNull
  String storageUrl;

}
