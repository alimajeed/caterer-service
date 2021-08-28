package com.hunzaconsulting.catererservice.utils;

import com.hunzaconsulting.catererservice.config.PropertyConfig;
import com.hunzaconsulting.catererservice.exception.BadRequestException;

public class AppUtils {
	public static void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size < 0) {
			throw new BadRequestException("Size number cannot be less than zero.");
		}

		if (size > PropertyConfig.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + PropertyConfig.MAX_PAGE_SIZE);
		}
	}
}
