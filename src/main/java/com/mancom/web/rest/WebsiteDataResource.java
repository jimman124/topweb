package com.mancom.web.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mancom.domain.WebsiteData;
import com.mancom.service.WebsiteDataService;

@RestController
@RequestMapping("/public")
public class WebsiteDataResource {

	@Inject
	private WebsiteDataService websiteDataService;

	private final Logger log = LoggerFactory.getLogger(WebsiteDataResource.class);

	@RequestMapping(value = "/websitedata/five/{downloadDateStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public List<WebsiteData> getTop5Website(@PathVariable String downloadDateStr) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date downloadDate = format.parse(downloadDateStr);

		List<WebsiteData> d = (List<WebsiteData>) websiteDataService.getAllWebsiteDataList(downloadDate, 5);
		return d;

	}

}
