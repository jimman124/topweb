package com.mancom.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mancom.domain.WebsiteData;
import com.mancom.repository.WebsiteDataRepository;

import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class WebsiteDataService {

	private final Logger log = LoggerFactory.getLogger(WebsiteDataService.class);

	@Inject
	private WebsiteDataRepository websiteDataRepository;

	@SuppressWarnings("unchecked")

	public List<WebsiteData> getAllWebsiteDataList(Date downloadDate, int top) {
		List<WebsiteData> datalist = getAllWebsiteDataList(downloadDate);
		List<WebsiteData> tmpList = new ArrayList<WebsiteData>();
		for (int i = 0; i < top; i++) {

			if (top > datalist.size())
				break;
			WebsiteData wsd = (WebsiteData) datalist.get(i);

			tmpList.add(wsd);
		}

		return tmpList;
	}

	public List<WebsiteData> getAllWebsiteDataList(Date downloadDate) {

		List<WebsiteData> datalist = (List<WebsiteData>) websiteDataRepository.findAll();

		List<WebsiteData> tmpList = new ArrayList<WebsiteData>();

		for (WebsiteData d : datalist) {

			if (d.getDownloadDate().compareTo(downloadDate) == 0) {

				tmpList.add(d);
			}
		}

		Collections.sort(tmpList, Collections.reverseOrder());

		return tmpList;
	}

}
