package com.locaplace.api.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NicknameGenerator {
    private final Random random = new Random();

    public String generate() {
        String emotion = getRandomElement(emotions);
        String animal = getRandomElement(animals);
        return String.format("%s %s", emotion, animal);
    }

    private String getRandomElement(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }

    public List<String> emotions = List.of("감미로운",
            "거대한",
            "경쾌한",
            "고급스러운",
            "고즈넉한",
            "고지식한",
            "과감한",
            "관대한",
            "굉장한",
            "귀여운",
            "급한",
            "기쁜",
            "기상천외한",
            "기운찬",
            "깔끔한",
            "깜깜한",
            "꼼꼼한",
            "꾸준한",
            "끈질긴",
            "낙천적인",
            "날카로운",
            "넉넉한",
            "놀라운",
            "느긋한",
            "늠름한",
            "능청스러운",
            "다정한",
            "단순한",
            "달콤한",
            "담백한",
            "당당한",
            "대담한",
            "독특한",
            "둔한",
            "따뜻한",
            "똑똒한",
            "뛰어난",
            "멋진",
            "무서운",
            "반가운",
            "발랄한",
            "밝은",
            "배부른",
            "변덕스러운",
            "부드러운",
            "불쌍한",
            "빛나는",
            "사랑스러운",
            "새로운",
            "섬세한");

    public List<String> animals = List.of("가재",
            "갈매기",
            "갈색곰",
            "강아지",
            "개구리",
            "개미",
            "고라니",
            "고래",
            "고릴라",
            "고양이",
            "고슴도치",
            "곰",
            "기니피그",
            "기린",
            "까마귀",
            "꿀벌",
            "나비",
            "낙타",
            "날다람쥐",
            "너구리",
            "노루",
            "늑대",
            "다람쥐",
            "닭",
            "달팽이",
            "당나귀",
            "대머리 독수리",
            "도마뱀",
            "돌고래",
            "돼지",
            "두더지",
            "두루미",
            "딱다구리",
            "말",
            "말미잘",
            "멧돼지",
            "물개",
            "미어캣",
            "박쥐",
            "백조",
            "병아리",
            "부엉이",
            "북극곰",
            "불독",
            "비둘기",
            "사막여우",
            "사슴",
            "사자",
            "상어",
            "새우");
}