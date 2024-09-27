package com.jjcdutra.livro_virtual.novolivro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
