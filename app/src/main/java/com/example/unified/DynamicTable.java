package com.example.unified;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DynamicTable {

    private TableLayout tableLayout;
    private Context context;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;

    public DynamicTable(TableLayout tableLayout, Context context, ArrayList<String[]> data){

        this.tableLayout = tableLayout;
        this.context = context;
        this.data = data;

    }
    public void CreateDataTable(){

        for(String[] row : data){
            newRow();
            for(String data : row){
                newCell(data);
                tableRow.addView(txtCell,getParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    private void newRow(){

        tableRow = new TableRow(context);

    }
    private void newCell(String text){

        txtCell = new TextView(context);
        txtCell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtCell.setTextSize(12);
        txtCell.setTextColor(Color.parseColor("#FFFFFF"));
        txtCell.setBackgroundColor(Color.parseColor("#100D12"));
        txtCell.setText(text);
    }
    private TableRow.LayoutParams getParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams(pxtodp(0),pxtodp(50));
        params.setMargins(pxtodp(1),pxtodp(1),pxtodp(1),pxtodp(1));
        return params;
    }
    private int pxtodp(float px){
        float pxDensity = context.getResources().getDisplayMetrics().density;
        return (int) (px * pxDensity);
    }


}
