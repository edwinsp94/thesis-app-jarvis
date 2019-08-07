#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress serverIP(192,168,1,105);

int serverPort = 8888;
int PIN_LED = 5;
int PIN_PUERTA = 6;
String readString;

EthernetServer server(serverPort);

void setup() {
  Serial.begin(9600);
  Ethernet.begin(mac, serverIP);
  server.begin();
  Serial.print("Server online.");
  pinMode(PIN_LED, OUTPUT);
  pinMode(PIN_PUERTA, OUTPUT);
}

void loop() {
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
          char c = client.read();
          if (readString.length() < 30) {
              readString.concat(c);
          }
          if (readString == "led1") {
            digitalWrite(PIN_LED, HIGH);
            Serial.println("Luz Encendida");
            resetString();
          }
          if (readString == "led0") {
            digitalWrite(PIN_LED, LOW);
            Serial.println("Luz Apagada");
            resetString();
          }
          if (readString == "abrir") {
            digitalWrite(PIN_PUERTA, HIGH);
            Serial.println("Puerta Abierta");
            resetString();
          }
          if (readString == "cerrar") {
            digitalWrite(PIN_PUERTA, LOW);
            Serial.println("Puerta Cerrada");
            resetString();
          }
      }
    }
    delay(1);
    client.stop();
  }
}

void resetString() {
    readString = "";
}
