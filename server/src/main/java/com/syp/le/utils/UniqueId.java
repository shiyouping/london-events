package com.syp.le.utils;

import java.util.UUID;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

/**
 * A UUID generator
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * @since Jun 29, 2019
 */
public class UniqueId {

	private UniqueId() {
	}

	/**
	 * A randomly generated UUID without "-", e.g.
	 * "dd3b755e966b44ccb08382fca238cd4a"
	 */
	@Nonnull
	public static String get() {
		return StringUtils.remove(UUID.randomUUID().toString(), "-");
	}
}
