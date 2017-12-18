package ru.devcorvette.infinitescroll.base.logic;

import ru.devcorvette.infinitescroll.base.logic.entity.CoverInfo;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.base.logic.entity.FeedResponse;

/**
 * Только для тестирования, если сервер http://109.111.162.236:8083/api/v2/ не работает.
 * Создает FeedResponse c Datum и ссылками на фото.
 */
public class TestDataService {

    private static String[][] urls = new String[][]{
            { "http://i1.2photo.ru/medium/w/7/727340.jpg",
                    "http://i2.2photo.ru/medium/x/7/727341.jpg",
                    "http://i1.2photo.ru/medium/y/7/727342.jpg",
                    "http://i2.2photo.ru/medium/z/7/727343.jpg"},

            { "http://i2.2photo.ru/medium/z/0/727091.jpg",
                    "http://i2.2photo.ru/medium/1/1/727093.jpg",
                    "http://i1.2photo.ru/medium/2/1/727094.jpg",
                    "http://i1.2photo.ru/medium/4/1/727096.jpg"	}	,

            {	"http://i1.2photo.ru/medium/8/x/726956.jpg",
                    "http://i1.2photo.ru/medium/a/x/726958.jpg",
                    "http://i2.2photo.ru/medium/b/x/726959.jpg",
                    "http://i2.2photo.ru/medium/h/x/726965.jpg"	}	,

            {	"http://i2.2photo.ru/medium/d/w/726925.jpg",
                    "http://i1.2photo.ru/medium/e/w/726926.jpg",
                    "http://i2.2photo.ru/medium/f/w/726927.jpg",
                    "http://i2.2photo.ru/medium/l/w/726933.jpg"	}	,

            {	"http://i2.2photo.ru/medium/z/s/726803.jpg",
                    "http://i1.2photo.ru/medium/0/t/726804.jpg",
                    "http://i1.2photo.ru/medium/2/t/726806.jpg"	}	,

            {	"http://i1.2photo.ru/medium/g/s/726784.jpg",
                    "http://i2.2photo.ru/medium/j/s/726787.jpg",
                    "http://i2.2photo.ru/medium/l/s/726789.jpg",
                    "http://i1.2photo.ru/medium/o/s/726792.jpg",}	,

            {	"http://i1.2photo.ru/medium/e/p/726674.jpg",
                    "http://i2.2photo.ru/medium/f/p/726675.jpg",
                    "http://i1.2photo.ru/medium/g/p/726676.jpg",
                    "http://i2.2photo.ru/medium/l/p/726681.jpg"	}	,

            {	"http://i2.2photo.ru/medium/z/j/726479.jpg",
                    "http://i1.2photo.ru/medium/0/k/726480.jpg",
                    "http://i2.2photo.ru/medium/5/k/726485.jpg"	}	,

            {	"http://i1.2photo.ru/medium/i/j/726462.jpg",
                    "http://i1.2photo.ru/medium/k/j/726464.jpg",
                    "http://i2.2photo.ru/medium/l/j/726465.jpg",
                    "http://i2.2photo.ru/medium/r/j/726471.jpg"	}	,

            {	"http://i2.2photo.ru/medium/v/e/726295.jpg",
                    "http://i2.2photo.ru/medium/x/e/726297.jpg",
                    "http://i1.2photo.ru/medium/y/e/726298.jpg",
                    "http://i2.2photo.ru/medium/z/e/726299.jpg"	}	,

            {	"http://i2.2photo.ru/medium/z/5/725975.jpg",
                    "http://i1.2photo.ru/medium/0/6/725976.jpg",
                    "http://i1.2photo.ru/medium/2/6/725978.jpg",
                    "http://i1.2photo.ru/medium/4/6/725980.jpg"	}	,

            {	"http://i2.2photo.ru/medium/n/5/725963.jpg",
                    "http://i1.2photo.ru/medium/o/5/725964.jpg",
                    "http://i2.2photo.ru/medium/r/5/725967.jpg",
                    "http://i1.2photo.ru/medium/u/5/725970.jpg"	}	,

            {	"http://i1.2photo.ru/medium/0/1/725796.jpg",
                    "http://i1.2photo.ru/medium/2/1/725798.jpg",
                    "http://i2.2photo.ru/medium/3/1/725799.jpg",
                    "http://i1.2photo.ru/medium/6/1/725802.jpg"	}	,

            {	"http://i2.2photo.ru/medium/j/r/725455.jpg",
                    "http://i1.2photo.ru/medium/k/r/725456.jpg",
                    "http://i1.2photo.ru/medium/o/r/725460.jpg",
                    "http://i2.2photo.ru/medium/p/r/725461.jpg"	}	,

            {	"http://i2.2photo.ru/medium/r/p/725391.jpg",
                    "http://i2.2photo.ru/medium/t/p/725393.jpg",
                    "http://i2.2photo.ru/medium/v/p/725395.jpg",
                    "http://i1.2photo.ru/medium/6/q/725406.jpg"	}	,

            {	"http://i2.2photo.ru/medium/1/e/724969.jpg",
                    "http://i1.2photo.ru/medium/2/e/724970.jpg",
                    "http://i2.2photo.ru/medium/3/e/724971.jpg",
                    "http://i2.2photo.ru/medium/9/e/724977.jpg"	}
    };

    /**
     * Создает Datum[], в каждый data кладет CoverInfo[] c ссылками на фото.
     */
    private static Datum[] createTestData(){
        Datum[] data = new Datum[urls.length];

        for (int i = 0; i < urls.length; i++) {

            Datum datum = new Datum();
            CoverInfo[] coverArray = new CoverInfo[urls[i].length];
            datum.setCoverInfo(coverArray);

            for (int j = 0; j < urls[i].length; j++) {
                CoverInfo ci = new CoverInfo();
                ci.setImage(urls[i][j]);
                coverArray[j] = ci;
            }
            data[i] = datum;
        }
        return data;
    }

    /**
     * Создает фейковый FeedResponse.
     */
    public static FeedResponse createFakeFeedResponse(){
        FeedResponse f = new FeedResponse();
        f.setData(createTestData());

        return f;
    }
}
