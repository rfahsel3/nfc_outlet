/*
  Web client
 
 This sketch connects to a website (http://www.google.com)
 using an Arduino Wiznet Ethernet shield. 
 
 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 
 created 18 Dec 2009
 modified 9 Apr 2012
 by David A. Mellis
 
 */

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = {  0x90, 0xA2, 0xDA, 0x00, 0xD8, 0xF8 };
IPAddress server(128,61,105,86); // Google
int mode=0;

// Initialize the Ethernet client library
// with the IP address and port of the server 
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;
int status=0;

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);
   while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");
}

void loop()
{
  int pinA=6;
  int pinB=7;
  char pinALink[200]="GET /checkPower.php?tid=1 HTTP/1.0";
  char pinBLink[200]="GET /checkPower.php?tid=2 HTTP/1.0";
  int doneWithRead=0;
  int activePin;
  // if you get a connection, report back via serial:
  if(mode%2==0)  {
    Serial.println("Pin A");
    if (client.connect(server, 80)) {
      Serial.println("connected");
      // Make a HTTP request:
      client.println(pinALink);
      client.println();
      activePin=pinA;
    } 
    else {
      // kf you didn't get a connection to the server:
      Serial.println("connection failed");
    }
  }
  else  {
    Serial.println("Pin B");
    if (client.connect(server, 80)) {
      Serial.println("connected");
      // Make a HTTP request:
      client.println(pinBLink);
      client.println();
      activePin=pinB;
    } 
    else {
      // kf you didn't get a connection to the server:
      Serial.println("connection failed");
    }
  } 
  mode+=1;
  
  while(doneWithRead==0)
  {
  //Temporary variable to keep tracke of each letter
  char c=0;
  //Counter for how long line is
  int total=0;
  //Blanks
  int blanks=0;
  //Keeps track of current line
  char line[500];
  //Auth value
  int auth=0;
  
  //Loop until carriage return is found
  while(c!=10 and blanks<10)
  {
    if (client.available()) {
      c = client.read();
      Serial.print(c);
      line[total]=c;
      Serial.println(total);
      total+=1;
    }
    else  {
      blanks+=1;
    }
  }
  Serial.println("GOT HERE NEW LINE");
  Serial.println("Total length of this line is: ");
  Serial.println(total);
  //Control LED depending on output
  if(status==1)  {
    Serial.println("Inside status loop");
    auth=line[0];
    Serial.println("Auth character is");
    Serial.println(auth);   
    //If, one on the server *(ASCII value for 1 is 49), power the led high
    if (auth==49)  {
      pinMode(activePin,OUTPUT);
      digitalWrite(activePin, HIGH);
    }
    else  {
      pinMode(activePin,OUTPUT);
      digitalWrite(activePin,LOW); 
    }
    status=0;
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
    doneWithRead=1;
  }
  //When indicator is detected, take next value on next loop
  if(total==2)  {
    status=1;
    Serial.println("SET STATUS");
  }

  }
}
