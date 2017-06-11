package com.springframework.cinema.domain.movie;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MovieValidator implements Validator {

	private final MovieService movieService;
	
	public MovieValidator(MovieRepository movieRepository) {
		movieService = new MovieService(movieRepository);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Movie.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "genre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "duration.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
		Movie movie = (Movie) object;
		if(movieService.checkIfTitleExists(movie.getTitle()))
			errors.rejectValue("title", "title.already_exists");
	}

}
