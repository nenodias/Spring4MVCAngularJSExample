package br.org.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.springmvc.dao.GenericDao;
import br.org.springmvc.dao.UserDao;
import br.org.springmvc.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserRestController extends GenericRestController<User, Long> {

	@Autowired
	private UserDao userDao;

	@Override
	protected GenericDao<User, Long> getDao() {
		return userDao;
	}

}
