package com.ahmad.shopforeveryone.SectionOrders.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmad.shopforeveryone.SectionOrders.Models.CustomItemClass;
import com.ahmad.shopforeveryone.SectionOrders.Models.CustomListBuyNow;
import com.ahmad.shopforeveryone.databinding.ActivityCheckOutBinding;
import com.ahmad.shopforeveryone.databinding.PaypalPaymentsBinding;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PaymentPaypal extends Fragment {


    PaypalPaymentsBinding paypalPaymentsBinding;
    String ClintID="Adt0wU-ULRhiDfvZp3KtAdHJ97KkwO90Wtjm_bYivwQ0CStJiLlXx8_rXLoVMPgcDZ7s88oq_MMGJ466";
    int paypalrequestCode=1;
    private static PayPalConfiguration configuration;
String amount="";
public  static  PaymentPaypal newInstance(Bundle bundle){
    PaymentPaypal paymentPaypal=new PaymentPaypal();
    paymentPaypal.setArguments(bundle);
    return paymentPaypal;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        paypalPaymentsBinding = PaypalPaymentsBinding.inflate(getLayoutInflater());
paypalPaymentsBinding.amountET.setText(amount);
        configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK).clientId(ClintID);
        paypalPaymentsBinding.paymentbtn.setOnClickListener(v -> {

            String amounts = paypalPaymentsBinding.amountET.getText().toString();
            if (Integer.parseInt(amounts)>0){
                PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amounts)), "USD", "feee", PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(requireContext(),  PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                startActivityForResult(intent, paypalrequestCode);

            }else{
            stateorders(false);
        }});
        return paypalPaymentsBinding.getRoot();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (requestCode==paypalrequestCode){

        PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
        if (confirmation!=null){
            try {
                String paymentdetails=confirmation.toJSONObject().toString();
                JSONObject jsonObject=new JSONObject(paymentdetails);
                stateorders(true);
            } catch (JSONException e) {

            }
        }else {
            requireActivity();
            if (requestCode==requireActivity().RESULT_CANCELED){
                stateorders(false);
            }
        }
    }else if (requestCode==PaymentActivity.RESULT_EXTRAS_INVALID){
     stateorders(false);
    }
    }

    private void stateorders(boolean stateOrders) {
        //to get frame id
        ActivityCheckOutBinding binding=ActivityCheckOutBinding.inflate(getLayoutInflater());
        Bundle bundle=getArguments();
        CustomListBuyNow listBuyNow=bundle.getParcelable("order");
        //add state order as boolean
        bundle.putBoolean("state",stateOrders);
        bundle.putParcelable("order",listBuyNow);
        StateCheckOut stateCheckOut=new StateCheckOut();
        stateCheckOut.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(binding.FramCheckout.getId(),stateCheckOut).addToBackStack(null).commit();

    }


    @Override
    public void onDestroy() {
   requireActivity().stopService(new Intent(requireContext(), PayPalService.class));
        super.onDestroy();
    }
}
