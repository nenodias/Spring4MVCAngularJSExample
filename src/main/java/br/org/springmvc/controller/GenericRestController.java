package br.org.springmvc.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.springmvc.dao.GenericDao;
import br.org.springmvc.model.AppModel;

@SuppressWarnings("rawtypes")
@Transactional
public abstract class GenericRestController<T extends AppModel, PK> {

	protected abstract GenericDao<T, PK> getDao();

	/*
	 * Listagem
	 * @RequestMapping(value = "/<entity>/", method = RequestMethod.GET)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<T>> listAll() {
		List<T> entities = getDao().findAll();
		if (entities.isEmpty()) {
			return new ResponseEntity<List<T>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<T>>(entities, HttpStatus.OK);
	}

	/*
	 * Listagem
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> get(@PathVariable("id") PK id) {
		T entity = getDao().findById(id);
		if (entity == null) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}

	/*
	 * Inserção
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody T entity,
			UriComponentsBuilder ucBuilder) {

		if (entity.getPK() != null) {
			T another = getDao().findById((PK) entity.getPK());
			if(another != null && another.getPK() != null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}

		getDao().save(entity);

		HttpHeaders headers = new HttpHeaders();
		/*
		headers.setLocation(ucBuilder.path("/user/{id}")
				.buildAndExpand(user.getId()).toUri());
				*/
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/*
	 * Atualização
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<T> update(@PathVariable("id") PK id,
			@RequestBody T entity) {
		T current = getDao().findById(id);

		if (current == null) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
		current.merge(entity);

		getDao().update(current);

		return new ResponseEntity<T>(getDao().initialize(current), HttpStatus.OK);
	}

	/*
	 * Exclusão
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<T> delete(@PathVariable("id") PK id) {
		T entity = getDao().findById(id);
		if (entity == null) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}

		getDao().delete(id);
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}

	/*
	 * Exclusão de Todos
	 */
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<T> deleteAll() {
		getDao().deleteAll();
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}
}
