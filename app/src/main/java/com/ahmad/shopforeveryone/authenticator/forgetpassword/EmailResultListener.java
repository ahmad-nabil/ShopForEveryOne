package com.ahmad.shopforeveryone.authenticator.forgetpassword;

public interface EmailResultListener {
    void EmailResult(Boolean ValueEmail);
    void CodeResult(Boolean ValueCode,String Code,String UID);

}
