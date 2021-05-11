package com.example.RedditClone.helpers;

import com.example.RedditClone.posts.Post;

import java.util.Comparator;

public class Comparators
{
    public static class PostVotesComparator implements Comparator<Post>
    {
        @Override
        public int compare(Post o1, Post o2) {

            if (o1.getPoints() < o2.getPoints()) { return 1; }
            else if (o1.getPoints() > o2.getPoints()) { return -1; }
            return 0;
        }
    }

    public static class PostDateComparator implements Comparator<Post>
    {
        @Override
        public int compare(Post o1, Post o2) {

            if (o1.getCreateTime() < o2.getCreateTime()) { return -1; }
            else if (o1.getCreateTime() > o2.getCreateTime()) { return 1; }
            return 0;
        }
    }
}
