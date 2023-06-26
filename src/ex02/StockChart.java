package ex02;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;

public class StockChart extends JFrame {
    private JTextArea chartArea;

    public StockChart() {
        setTitle("주식 차트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        chartArea = new JTextArea();
        chartArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chartArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        fetchStockData();

        setVisible(true);
    }

    private void fetchStockData() {
        String apiKey = "UiWfCnWS8oDdkdhn7u9t5p7bF1xkK5Jr"; // Polygon.io API 키
        String symbol = "AAPL"; // 주식 심볼
        String apiUrl = "https://api.polygon.io/v2/aggs/ticker/" + symbol + "/range/1/day/2022-01-01/2022-12-31?apiKey=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // JSON 데이터 파싱
                Gson gson = new Gson();
                ChartData chartData = gson.fromJson(response.toString(), ChartData.class);

                // 차트에 데이터 표시
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                for (ChartData.Result result : chartData.getResults()) {
                    String dateStr = dateFormat.format(new Date(result.getT()));
                    chartArea.append(dateStr + " - " + result.getC() + "\n");
                }
            } else {
                System.out.println("HTTP 요청 실패: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StockChart::new);
    }
}

class ChartData {
    private String status;
    private int queryCount;
    private int resultsCount;
    private boolean adjusted;
    private Result[] results;

    public String getStatus() {
        return status;
    }

    public int getQueryCount() {
        return queryCount;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public boolean isAdjusted() {
        return adjusted;
    }

    public Result[] getResults() {
        return results;
    }

    class Result {
        private long t;
        private double c;

        public long getT() {
            return t;
        }

        public double getC() {
            return c;
        }
    }
}
