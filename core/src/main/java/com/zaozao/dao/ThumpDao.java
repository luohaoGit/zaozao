package com.zaozao.dao;

import com.zaozao.model.po.Thump;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2015/11/11.
 */
@Repository
public interface ThumpDao extends BaseDao<Thump> {

    long countByPostId(String postId);

}
