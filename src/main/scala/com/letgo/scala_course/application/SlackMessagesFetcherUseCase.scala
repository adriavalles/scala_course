package com.letgo.scala_course.application

import scala.concurrent.{ExecutionContext, Future}

import com.letgo.scala_course.domain.{ChannelId, Message, SlackClient}

class SlackMessagesFetcherUseCase(slackClient: SlackClient)(implicit ec: ExecutionContext) {
  var numberOfApiCalls: Int = 0

  var cache: Option[Future[Seq[Message]]] = None

  def fetch(channelName: ChannelId): Future[Seq[Message]] = {
    val result = slackClient.fetchChannelMessages(channelName)
    numberOfApiCalls += 1
    result
  }

  def fetchWithCache(channelName: ChannelId): Future[Seq[Message]] = {
    cache.getOrElse{
      val messages = fetch(channelName)
      cache = Some(messages)
      messages
    }
  }
}
