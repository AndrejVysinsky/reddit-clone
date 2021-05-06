package com.example.RedditClone.helpers;

public class Parameters
{
    public static class PostParams
    {
        public static String postId = "postId";
        public static String postHeader = "postHeader";
        public static String postBody = "postBody";
        public static String postPoints = "postPoints";
        public static String createTime = "createTime";
    }

    public static class UserParams
    {
        public static String userId = "userId";
        public static String userName = "userName";
        public static String userPassword = "userPassword";
        public static String userPasswordConfirmation = "userPasswordConfirmation";
        public static String userImage = "userImage";
    }

    public static class PostVoteParams
    {
        public static String isUpvote = "isUpvote";
    }

    public static class SessionParams
    {
        public static String sessionUser = "sessionUser";
    }
}
