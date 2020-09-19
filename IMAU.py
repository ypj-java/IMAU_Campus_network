# -*-coding:utf-8-*-
import  requests

usename = ""
passwd = ""

def log(action):
    url="http://login.imau.edu.cn:801/srun_portal_pc.php?ac_id=1&"
    params={
       "action":action,
       "username":usename,
       "password":passwd,
       "ajax":"1"
    }
    if action == "login":
        params["ac_id"] = "1"
        params["user_ip"] = ""
        params["nas_ip"] = ""
        params["user_mac"] = ""
        params["save_me"] = "0"
    if action == "logout":
        pass

    html=requests.post(url,data=params)
    print(html.text)

def login():
    log("login")

def logout():
    log("logout")

if __name__ == '__main__':
    login()