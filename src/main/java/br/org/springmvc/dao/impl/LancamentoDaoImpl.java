package br.org.springmvc.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.springmvc.dao.LancamentoDao;
import br.org.springmvc.model.Lancamento;

@Repository
@Transactional
public class LancamentoDaoImpl extends GenericDaoImpl<Lancamento, Long> implements LancamentoDao {

}
