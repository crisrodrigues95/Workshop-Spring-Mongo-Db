package com.cristhian.workshopmongo.resourses;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristhian.workshopmongo.domain.Post;
import com.cristhian.workshopmongo.resourses.util.URL;
import com.cristhian.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="txt", defaultValue="") String txt) {
		txt = URL.decomeParam(txt);
		List<Post> list = service.findByTitle(txt);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/fullsearch",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value="txt", defaultValue="") String txt,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		txt = URL.decomeParam(txt);
		Date min= URL.convertDate(minDate, new Date(0L));
		Date max= URL.convertDate(maxDate, new Date());
		
		List<Post> list = service.fullSearch(txt, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
	
}
