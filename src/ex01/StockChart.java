package ex01;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;

public class StockChart extends JFrame {
    private ChartPanel chartPanel;

    public StockChart() {
        setTitle("주식 차트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // 프레임의 상단 중앙에 버튼을 추가하여 "애플 주식차트"라는 텍스트가 출력되도록 수정
        JButton button = new JButton("Apple Stock Chart");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setEnabled(false); // 버튼 비활성화
        add(button, BorderLayout.NORTH);

        chartPanel = new ChartPanel();
        add(chartPanel);

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

                // 차트 데이터 설정
                chartPanel.setChartData(chartData);
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
    private String status;        // API 요청 상태
    private int queryCount;       // API 요청 횟수
    private int resultsCount;     // 결과 수
    private boolean adjusted;     // 조정 여부
    private Result[] results;     // 주식 차트 데이터 배열

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
        private long t;      // 시간 정보 (Unix Timestamp)
        private double c;    // 종가 (Closing Price)

        public long getT() {
            return t;
        }

        public double getC() {
            return c;
        }
    }
}

class ChartPanel extends JPanel {
    private ChartData chartData;

    public void setChartData(ChartData chartData) {
        this.chartData = chartData;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (chartData == null || chartData.getResults() == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (ChartData.Result result : chartData.getResults()) {
            double value = result.getC();
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }
        }

        int xGap = width / (chartData.getResults().length + 1);
        double yScale = (double) (height - 20) / (maxValue - minValue);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);

        // y축 눈금 및 숫자 그리기
        int yTickCount = 5; // y축 눈금 개수
        int yGap = (height - 20) / yTickCount;
        double yStep = (maxValue - minValue) / yTickCount;
        for (int i = 0; i <= yTickCount; i++) {
            int y = height - 10 - i * yGap;
            double value = minValue + i * yStep;
            String label = String.format("%.2f", value);
            g2d.drawLine(40, y, 50, y);
            g2d.drawString(label, 10, y + 5);
        }

        // x축 눈금 및 숫자 그리기
        int xTickCount = chartData.getResults().length;
        int xGapSize = xGap / 2;
        for (int i = 0; i < xTickCount; i++) {
            // x축에 숫자를 출력할 위치를 10배수로 변경
            if ((i + 1) % 10 == 0) {
                int x = (i + 1) * xGap;
                g2d.drawLine(x, height - 10, x, height);
                String label = String.valueOf(i + 1);
                g2d.drawString(label, x - xGapSize, height - 5);
            }
        }

        for (int i = 0; i < chartData.getResults().length - 1; i++) {
            int x1 = (i + 1) * xGap;
            int y1 = (int) ((maxValue - chartData.getResults()[i].getC()) * yScale) + 10;
            int x2 = (i + 2) * xGap;
            int y2 = (int) ((maxValue - chartData.getResults()[i + 1].getC()) * yScale) + 10;

            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
