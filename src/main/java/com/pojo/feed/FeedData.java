package com.pojo.feed;

public record FeedData(
  ValidationErrorData validation,
  SubscriptionData subscribe,
  SuccessData success
) {}
