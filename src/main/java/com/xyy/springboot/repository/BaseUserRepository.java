package com.xyy.springboot.repository;

import com.xyy.springboot.domain.BaseUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.Table;

@Repository
@Table(name="base_user")
@Qualifier("userRepository")
public interface BaseUserRepository extends CrudRepository<BaseUser, Long > {

    public BaseUser getById(Long id);

    public BaseUser save(BaseUser u);

    @Query("select t from BaseUser t where t.name=:name")
    public BaseUser findUserByName(@Param("name") String name);
}