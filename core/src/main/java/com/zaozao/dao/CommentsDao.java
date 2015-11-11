package com.zaozao.dao;

import com.zaozao.model.po.Comments;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2015/11/11.
 */
@Repository
public interface CommentsDao extends BaseDao<Comments> {

    long countByPostId(String postId);

}
