package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
*/
public class MainActivity extends AppCompatActivity {
private RetrofitClient retrofitClient;
private List<WeatherData> weatherDataList;
private String weatherdata;
private TextView tempTV;
private TextView windTV;
private TextView cloudTV;
private TextView precipitationTV;
private String tempData;
private String windData;
private String cloudData;
private String precipitationData;
private String weatherIconData;
private ImageView weatherIcon;
private Button refreshBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempTV = findViewById(R.id.temp);
        windTV = findViewById(R.id.wind);
        cloudTV = findViewById(R.id.cloud);
        precipitationTV = findViewById(R.id.precipitation);
        weatherIcon = findViewById(R.id.weatherImage);
        refreshBtn = findViewById(R.id.refreshButton);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherData();
                setWeatherData();
            }
        });

    }
    public void setWeatherData(){
        tempTV.setText(tempData);
        windTV.setText(windData);
        cloudTV.setText(cloudData);
        precipitationTV.setText(precipitationData);
        int id = getResources().getIdentifier("drawable/" + weatherIconData,null,getPackageName());
        weatherIcon.setImageResource(id);
    }

    public void getWeatherData(){
        Thread thread = new Thread(){
            public void run(){

                System.out.println("Thread Running");
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.met.no/weatherapi/locationforecast/2.0/classic?&lat=60.10&lon=9.58")
                        .get()
                        .addHeader("User-agent", "https://github.com/joelgoransson/DT142G-Projektgrupp-Java")

                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    weatherdata= response.body().string();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    InputSource src = new InputSource();
                    src.setCharacterStream(new StringReader(weatherdata));

                    Document doc = builder.parse(src);
                    NamedNodeMap temperature = doc.getElementsByTagName("temperature").item(0).getAttributes();
                    NamedNodeMap windspeed = doc.getElementsByTagName("windSpeed").item(0).getAttributes();
                    NamedNodeMap windDirection = doc.getElementsByTagName("windDirection").item(0).getAttributes();
                    NamedNodeMap cloudiness = doc.getElementsByTagName("cloudiness").item(0).getAttributes();
                    NamedNodeMap precipitation = doc.getElementsByTagName("precipitation").item(1).getAttributes();
                    NamedNodeMap symbol = doc.getElementsByTagName("symbol").item(0).getAttributes();

                    String weatherIconId = symbol.item(0).getNodeValue();
                    System.out.println(weatherIconId);

                    //tempvalues as Strings
                    String temptype = temperature.item(1).getNodeValue();
                    String tempvalue = temperature.item(2).getNodeValue();

                    //Windvalues as Strings
                    String windmps = windspeed.item(1).getNodeValue();
                    String windDirectionName = windDirection.item(2).getNodeValue();

                    //cloudvalues as Strings
                    String cloudpercent = cloudiness.item(1).getNodeValue();

                    //raininfo as strings
                    String precipitationunit = precipitation.item(0).getNodeValue();
                    String precipitationmin = precipitation.item(2).getNodeValue();
                    String precipitationmax = precipitation.item(3).getNodeValue();

                    //saves the weather data in class variables
                    tempData = tempvalue + " " + temptype;
                    windData = windmps + "mps, toward " + windDirectionName;
                    cloudData = cloudpercent + "%";
                    precipitationData = "Between " + precipitationmin + " " + precipitationunit + " and " + precipitationmax + " " + precipitationunit;
                    weatherIconData = weatherIconId.toLowerCase();

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }



    };



/*
    private void getWeatherData(){
        //lat=60.10;lon=9.58
        String lat = "60.10";
        String lon = "9.58";
        retrofitClient = RetrofitClient.getINSTANCE();
        Call<WeatherDataList> call = retrofitClient.getWeatherData(lat,lon);

        call.enqueue(new Callback<WeatherDataList>() {
            @Override
            public void onResponse(Call<WeatherDataList> call, Response<WeatherDataList> response) {
                Log.d("Conneciton succes", response.message());
                //weatherDataList = response.body().getWeatherList();
                String temp2 = response.body().toString();
                String temp = response.body().toString();
                System.out.println("");
            }

            @Override
            public void onFailure(Call<WeatherDataList> call, Throwable t) {
                Log.d("Conneciton failed", t.getMessage());
                System.out.println("");
            }
        });

    }

 */
}


