package com.example.springbootnewsportal.mapper;

import com.example.springbootnewsportal.entity.Post;
import com.example.springbootnewsportal.web.model.request.post.UpsertPostRequest;
import com.example.springbootnewsportal.web.model.response.post.BriefPostResponse;
import com.example.springbootnewsportal.web.model.response.post.PostListResponse;
import com.example.springbootnewsportal.web.model.response.post.PostResponse;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(PostMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PostMapper {

    Post requestToPost(UpsertPostRequest request);

    @Mapping(source = "postId", target = "id")
    Post requestToPost(Long postId, UpsertPostRequest request);


    PostResponse postToResponse(Post post);

    BriefPostResponse postToBriefResponse(Post post);


    List<BriefPostResponse> postListToResponseList(List<Post> posts);

    default PostListResponse postListToPostListResponse(List<Post> posts) {
        PostListResponse response = new PostListResponse();
        response.setPosts(postListToResponseList(posts));
        return response;
    }

}