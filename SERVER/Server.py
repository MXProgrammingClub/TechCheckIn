#PyChat LAN server 2.0
import socket
import thread
import sys
import signal
import os.path
import json
from MessageQueue import MessageQueue

CONFIG = "serv_conf.txt";
comms = [];

def newClient(conn,addr):
    conn.sendall("Enter your name: ");
    name = conn.recv(optdict['nm_len']);
    time = conn.recv(optdict['time_len']);
    isSignIn = conn.recv(1);
    comms =+ (name,time,isSignIn);
    log((name,time,isSignIn));
    conn.close();

def log(data):
    

def acceptClients(sock):
    s.listen(optdict['max_clients']);
    while True:         #Wait for new connections, spin out new thread w/ sock, hand thread ref to msgQ
        client,addr = s.accept();
        msgQ.log("Accepting new client...");
        thread.start_new_thread(newClient,(client,addr));

if os.path.isfile(CONFIG):
    print "Found config file, reading prefrences from " + CONFIG;
    conf = open(CONFIG, 'r');
    optdict = json.loads(conf.read());
    conf.close();
else:
    print "No config file found, generating and writing default file";
    optdict = {
        'sleep_time' : 1,
        'time_len' : 5,
        'nm_len' : 16,
        'hostname' : 'DEFAULT',
        'port' : 90,
        'max_clients' : 50,
        'logfile' : 'serv_log.txt'
        }
    default = open(CONFIG,'w');
    default.write(json.dumps(optdict));
    default.close();
    print "Default config written to " + CONFIG;

logFile = open(optdict['logfile'],'a');
s = socket.socket();
s.bind((socket.gethostname() if optdict['hostname'] == "DEFAULT" else optdict['hostname'],optdict['port']));
acceptClients(s);           #Wait for incoming connections on specified port and add connections to messageQueue when initialized
