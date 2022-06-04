package com.seoridam.rehearserver.fixture;

import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.global.common.JobEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.seoridam.rehearserver.global.common.JobEnum.PROFESSIONAL;
import static com.seoridam.rehearserver.global.common.JobEnum.STUDENT;

public class UserFixture {

    public static class User1 {
        public static final Long ID = 1L;
        public static final String NAME = "user1";
        public static final String EMAIL = "v@test.com";
        public static final String PASSWORD = "dlafjajdfljnfjdnajkn";
        public static final String NICKNAME = "user1_nickname";
        public static final int AGE = 20;
        public static final boolean VENTURE_FLAG = true;
        public static final Integer VENTURE_YEAR =  0;
        public static final JobEnum JOB = STUDENT;
        public static final String BUSINESS_CATEGORY = "스타트업";
        public static final List<String> ROLES = new ArrayList<>(Collections.singletonList("ROLE_USER"));

        public static final User USER = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .age(AGE)
                .ventureFlag(VENTURE_FLAG)
                .ventureYear(VENTURE_YEAR)
                .job(JOB)
                .businessCategory(BUSINESS_CATEGORY)
                .roles(ROLES)
                .build();
    }

    public static class User2 {
        public static final Long ID = 2L;
        public static final String NAME = "user2";
        public static final String EMAIL = "user2@test.com";
        public static final String PASSWORD = "user2";
        public static final String NICKNAME = "user2_nickname";
        public static final int AGE = 30;
        public static final boolean VENTURE_FLAG = false;
        public static final Integer VENTURE_YEAR = 1;
        public static final JobEnum JOB = PROFESSIONAL;
        public static final String BUSINESS_CATEGORY = "스타트업";
        public static final List<String> ROLES = new ArrayList<>(Collections.singletonList("ROLE_USER"));

        public static final User USER = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .age(AGE)
                .ventureFlag(VENTURE_FLAG)
                .ventureYear(VENTURE_YEAR)
                .job(JOB)
                .businessCategory(BUSINESS_CATEGORY)
                .roles(ROLES)
                .build();
    }
}
