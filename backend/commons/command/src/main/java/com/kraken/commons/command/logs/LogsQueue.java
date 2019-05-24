package com.kraken.commons.command.logs;

import com.kraken.commons.command.entity.Command;
import com.kraken.commons.command.entity.CommandLog;
import com.kraken.commons.command.entity.CommandLogStatus;
import reactor.core.publisher.FluxSink;

public interface LogsQueue {

  void addListener(String id, String applicationId, FluxSink<CommandLog> listener);

  void removeListener(String id);

  CommandLog publish(Command command, CommandLogStatus status, String text);

  void clear();

}
