package com.hunzaconsulting.catererservice.utils;

import com.hunzaconsulting.catererservice.config.PropertyConfig;
import com.hunzaconsulting.catererservice.exception.BadRequestException;
import com.hunzaconsulting.catererservice.payload.ApiResponse;

public class AppUtils {
	public static void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Page number cannot be less than zero.");
			throw new BadRequestException(apiResponse);
		}

		if (size < 0) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Size number cannot be less than zero.");
			throw new BadRequestException(apiResponse);
		}

		if (size > PropertyConfig.MAX_PAGE_SIZE) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Page size must not be greater than " + PropertyConfig.MAX_PAGE_SIZE);
			throw new BadRequestException(apiResponse);
		}
	}
}
