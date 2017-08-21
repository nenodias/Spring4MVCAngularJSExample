package br.org.springmvc.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.springmvc.dao.PessoaDao;
import br.org.springmvc.model.Pessoa;

@Repository
@Transactional
public class PessoaDaoImpl extends GenericDaoImpl<Pessoa, Long> implements PessoaDao{

}
