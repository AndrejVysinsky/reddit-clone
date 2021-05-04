package com.example.RedditClone.posts;

import com.example.RedditClone.helpers.ParameterMapping;
import com.example.RedditClone.helpers.Parameters;
import com.example.RedditClone.users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsController
{


    public List<Post> getAllPosts()
    {
        return null;
    }

    public Post MapParamsToPost(Map<String, String[]> params)
    {
        HashMap<String, String> trimmedParams = ParameterMapping.trimParamMap(params);

        User author = null; // get author by Id

        if (author == null)
            return null;

        Post post = new Post(
                trimmedParams.get(Parameters.PostParams.postHeader),
                trimmedParams.get(Parameters.PostParams.postBody),
                author,
                0
        );

        return post;
    }
}
