package br.org.springmvc.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.springmvc.dao.UserDao;
import br.org.springmvc.model.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

}
