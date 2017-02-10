package com.letgo.scala_course.domain.service

import com.letgo.scala_course.domain.Message

class MessageCensor(forbiddenKeywords: Set[String]) {

  private val filterRule: Message => Message = { message =>
    forbiddenKeywords.foldLeft(message) { (textMessage, forbiddenKeyword) =>
      message.copy(text = textMessage.text.replace(forbiddenKeyword, "").replace("  ", " ").trim)
    }
  }

  //  private val filterRule: Message => Message = { message =>
  //    forbiddenKeywords.foldLeft(message) { (textMessage, forbiddenKeyword) =>
  //      Message(textMessage.text.replace(forbiddenKeyword, "").replace("  ", " ").trim)
  //    }
  //  }

  //  private val filterRule: Message => Message = { message =>
  //    var text = message.text
  //    forbiddenKeywords.foreach(forbiddenKeyword => text = text.replace(forbiddenKeyword, "")).replace("  ", " ").trim
  //    Message(text)
  //  }

  def filterMessages(messages: Seq[Message]): Seq[Message] = messages.map(filterRule)

}

