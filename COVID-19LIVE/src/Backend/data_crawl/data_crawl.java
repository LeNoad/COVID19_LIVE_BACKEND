package Backend.data_crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Backend.Time.dateTime;

public class data_crawl {
	private Document doc;
	private String government_url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";
	
	public data_crawl() {
		try {
			doc = Jsoup.connect(government_url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String table_coviddata(int i) { //Table명 covid_data 배열[2] 저장
		Elements parse_date = doc.select(".number");
		String string_parse_date = parse_date.text().replaceAll(",", "");
		String[] rs = string_parse_date.split(" ");
		return rs[i];
	}
	
	public String table_covidmap(int k) { //Table명 covid_map 배열[16] 저장
		String[] js = new String[17];
		int j = 0;
		for(int i=11; i<=139; i+=8) {
			js[j] = covidmap_array(i).replace(",", "");
			j++;
		}
		return js[k];
	}
	
	public String covidmap_array(int i) { //Table명 covid_map 전체 배열 받아오기
		Elements parse_date = doc.select("td.number");
		String str = parse_date.text();
		String[] rs = str.split(" ");
		return rs[i];
	}
	
	public String table_covidgraph() { //Table명 covid_graph 배열[] 저장
		Elements parse_date = doc.select("td.number");
		String str = parse_date.text();
		String[] rs = str.split(" ");
		return rs[0];
	}
	public static void main(String[] args) {
		data_crawl data = new data_crawl();
		System.out.println(data.table_covidgraph());
	}
}
