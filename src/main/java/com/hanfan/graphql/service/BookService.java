package com.hanfan.graphql.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanfan.graphql.model.Book;
import com.hanfan.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public DataFetcher<CompletableFuture<Book>> getBook() {
		return env -> {
			int bookId = env.getArgument("id");
			return bookRepository.getBook(bookId).toFuture();
		};
	}

	public DataFetcher<CompletableFuture<List<Book>>> getBooks() {
		return env -> bookRepository.getBooks().collectList().toFuture();
	}
}
