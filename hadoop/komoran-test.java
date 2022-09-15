import java.util.List;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

public class test {
	public static void main(String[] args) throws IOException{
        File file = new File("./data/wordcount-data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while(((line = br.readLine()) != null))
            sb.append(line);

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        String sourceStr = sb.toString();
        KomoranResult resultList = komoran.analyze(sourceStr);
        List<Token> tokenList = resultList.getTokenList();
        for(Token token : tokenList) {
            if(token.getPos().equals("NNP") || token.getPos().equals("NNG")) {
				System.out.println(token.getMorph());
			}
		}
	}
}

