package com.dimitris.kasiaras.actionmonitor.adapter.storage.reporitory;

import com.dimitris.kasiaras.actionmonitor.adapter.storage.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
