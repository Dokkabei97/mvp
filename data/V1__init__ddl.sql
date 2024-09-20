-- 회원 테이블
CREATE TABLE members
(
  member_id  SERIAL PRIMARY KEY,
  name       VARCHAR(100)        NOT NULL,
  nickname   VARCHAR(50) UNIQUE  NOT NULL,
  password   VARCHAR(255)        NOT NULL,
  email      VARCHAR(100) UNIQUE NOT NULL,
  gender     VARCHAR(6) CHECK (gender IN ('MALE', 'FEMALE')), -- 성별: MALE(남성), FEMALE(여성)
  birth_date DATE                NOT NULL,                    -- 출생일
  location   VARCHAR(100)        NOT NULL,                    -- 회원 위치 정보
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 가게 테이블
CREATE TABLE shops
(
  shop_id          SERIAL PRIMARY KEY,
  member_id        INT REFERENCES members (member_id) ON DELETE CASCADE, -- 가게 등록 회원
  shop_name        VARCHAR(100) NOT NULL,
  shop_description TEXT,
  created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 가게 방문 로그 테이블
CREATE TABLE shop_visit_logs
(
  log_id     SERIAL PRIMARY KEY,
  member_id  INT REFERENCES members (member_id) ON DELETE CASCADE, -- 방문한 회원
  shop_id    INT REFERENCES shops (shop_id) ON DELETE CASCADE,     -- 방문한 가게
  visit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP                   -- 방문 시간
);

-- 메뉴 테이블
CREATE TABLE menus
(
  menu_id          SERIAL PRIMARY KEY,
  shop_id          INT REFERENCES shops (shop_id) ON DELETE CASCADE, -- 메뉴가 속한 가게
  menu_name        VARCHAR(100)   NOT NULL,
  menu_description TEXT,
  price            DECIMAL(10, 2) NOT NULL,
  category         VARCHAR(50),                                      -- 메뉴 카테고리 (한식, 중식, 일식 등)
  order_count      INT       DEFAULT 0,                              -- 메뉴 주문 횟수
  created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 가게 리뷰 테이블
CREATE TABLE shop_reviews
(
  review_id   SERIAL PRIMARY KEY,
  shop_id     INT REFERENCES shops (shop_id) ON DELETE CASCADE,     -- 리뷰가 속한 가게
  member_id   INT REFERENCES members (member_id) ON DELETE CASCADE, -- 리뷰 작성 회원
  rating      INT CHECK (rating BETWEEN 1 AND 5),                   -- 별점 (1~5)
  review_text TEXT,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 메뉴 리뷰 테이블
CREATE TABLE menu_reviews
(
  review_id   SERIAL PRIMARY KEY,
  menu_id     INT REFERENCES menus (menu_id) ON DELETE CASCADE,     -- 리뷰가 속한 메뉴
  member_id   INT REFERENCES members (member_id) ON DELETE CASCADE, -- 리뷰 작성 회원
  rating      INT CHECK (rating BETWEEN 1 AND 5),                   -- 별점 (1~5)
  review_text TEXT,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 리뷰에 대한 좋아요 테이블 (가게 리뷰)
CREATE TABLE review_likes
(
  like_id    SERIAL PRIMARY KEY,
  review_id  INT REFERENCES shop_reviews (review_id) ON DELETE CASCADE, -- 가게 리뷰에 대한 좋아요
  member_id  INT REFERENCES members (member_id) ON DELETE CASCADE,      -- 좋아요를 누른 회원
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 리뷰에 대한 좋아요 테이블 (메뉴 리뷰)
CREATE TABLE menu_review_likes
(
  like_id    SERIAL PRIMARY KEY,
  review_id  INT REFERENCES menu_reviews (review_id) ON DELETE CASCADE, -- 메뉴 리뷰에 대한 좋아요
  member_id  INT REFERENCES members (member_id) ON DELETE CASCADE,      -- 좋아요를 누른 회원
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 리뷰 응답 테이블 (가게 리뷰)
CREATE TABLE review_responses
(
  response_id   SERIAL PRIMARY KEY,
  review_id     INT REFERENCES shop_reviews (review_id) ON DELETE CASCADE, -- 응답한 리뷰
  member_id     INT REFERENCES members (member_id) ON DELETE CASCADE,      -- 응답한 회원
  response_text TEXT NOT NULL,                                             -- 응답 내용
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 결제 테이블 (구독형 서비스)
CREATE TABLE subscriptions
(
  subscription_id   SERIAL PRIMARY KEY,
  member_id         INT REFERENCES members (member_id) ON DELETE CASCADE, -- 구독한 회원
  subscription_type VARCHAR(50)    NOT NULL,                              -- 구독 종류 (예: 기본, 프리미엄 등)
  price             DECIMAL(10, 2) NOT NULL,                              -- 결제 금액
  start_date        DATE           NOT NULL,                              -- 구독 시작일
  end_date          DATE           NOT NULL,                              -- 구독 종료일
  payment_method    VARCHAR(50),                                          -- 결제 방식 (카드, 현금 등)
  is_active         BOOLEAN   DEFAULT TRUE,                               -- 구독 활성화 여부
  is_cancelled      BOOLEAN   DEFAULT FALSE,                              -- 구독 취소 여부
  created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 구독 만료 7일 전 알람 기능
CREATE
OR REPLACE FUNCTION subscription_expiry_alert()
RETURNS TRIGGER AS $$
BEGIN
    -- 구독 만료 7일 전 알람을 위한 로직 추가
    IF
NEW.end_date <= CURRENT_DATE + INTERVAL '7 days' THEN
        -- 알람 기능 로직을 이곳에 추가 (예: 이메일 발송 등)
        RAISE NOTICE 'Subscription for member % will expire in 7 days.', NEW.member_id;
END IF;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER subscription_alert_trigger
  AFTER UPDATE
  ON subscriptions
  FOR EACH ROW
  EXECUTE FUNCTION subscription_expiry_alert();
