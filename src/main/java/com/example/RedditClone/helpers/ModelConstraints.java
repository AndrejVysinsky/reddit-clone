package com.example.RedditClone.helpers;

public class ModelConstraints
{
    public static class UserConstraints
    {
        public static int minUsernameLength = 5;
        public static int maxUsernameLength = 20;

        public static int minPasswordLength = 6;
        public static int maxPasswordLength = 30;
    }

    public static class PostConstraints
    {
        public static int minPostHeaderLength = 3;
        public static int maxPostHeaderLength = Integer.MAX_VALUE;

        public static int minPostBodyLength = 3;
        public static int maxPostBodyLength = Integer.MAX_VALUE;
    }
}
