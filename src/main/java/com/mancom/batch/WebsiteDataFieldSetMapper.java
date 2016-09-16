package com.mancom.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mancom.domain.WebsiteData;

public class WebsiteDataFieldSetMapper implements FieldSetMapper<WebsiteData> {

	@Override
	public WebsiteData mapFieldSet(FieldSet fieldSet) throws BindException {

		WebsiteData websiteData = new WebsiteData();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		try {
			websiteData.setDownloadDate(sdf.parse(fieldSet.readString("downloadDate")));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		websiteData.setUrl(fieldSet.readString("url"));
		websiteData.setCounter(fieldSet.readLong("counter"));
		return websiteData;
	}

}