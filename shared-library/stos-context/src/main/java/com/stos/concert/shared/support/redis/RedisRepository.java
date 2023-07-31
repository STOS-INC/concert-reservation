package com.stos.concert.shared.support.redis;

import org.springframework.util.StringUtils;

public interface RedisRepository {
	RedisKeyType type();

	/**
	 * {@link RedisKeyType} 에 Type 정의
	 * <p>
	 * Entity Type 별로 slot 에 저장
	 * <p> <b>format</b> > {key_type}:id
	 */
	default String typeKey(RedisKeyType type, Object id) {
		return type.hashTag() + ":" + id;
	}

	default String typeKey(Object id) {
		if (type() == RedisKeyType.DEFAULT) {
			return String.valueOf(id);
		}
		return typeKey(type(), id);
	}

	/**
	 * id 별로 slot 에 저장
	 * <p> <b>format</b> > {id}:key1#key2
	 */
	default String idKey(Object id, Object... keys) {
		return String.format("{%s}:%s", id, StringUtils.arrayToDelimitedString(keys, "#"));
	}

}
