package com.ibm.academia.ruleta.apirest.services;

import java.util.Optional;


public interface GenericoDAO<E>
{
	public Optional<E> buscarPorId(Long id);
	public E guardar(E entidad);
	public Iterable<E> buscarTodos();
	public void eliminarPorId(Long id);
}

