package com.sanctaultras.encryptedchat.user.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

    Page<Message> findByParentChatRoom_Id(Long id,Pageable pageable);
}
