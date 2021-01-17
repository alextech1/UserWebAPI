using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Net.Http;
using UserWebAPI.Entities;
using System.Text;
using System.Net;
using UserWebAPI.Models;
using System.IO;
using Nancy.Json;

namespace UserWebAPI.Data
{
    public class PushNotificationLogic
    {
        private static Uri FireBasePushNotificationURL = new Uri("https://fcm.googleapis.com/fcm/send");
        private static string ServerKey = "AAAAOS67KR8:APA91bF2pamPJN7BxZyrMRx9RksmPHqNnAWy_rUJG2fSuDxW5WucWzqMRNqmuDJFSY9weLMp_6-Q_aZ1GaC7NWs8PCO48dfvNhCRMoml7CpOjrEhY5RWdxPvSAw7J4UO2T3hhKGqrXw_";
        private static string SenderId = "245597153567";
        private DataContext dataContext;

        public PushNotificationLogic(DataContext context)
        {
            dataContext = context;
        }

        // Firebase onMessageReceived expects a certain json format.
        public void PushNotification(string message, string tagMsg, string userName)
        {
            try
            {
                string applicationID = ServerKey;

                string senderId = SenderId;

                User user = dataContext.Users.Where(t => t.UserName.CompareTo(userName) == 0).FirstOrDefault();
                if (user.Token != null)
                {
                    string deviceId = user.Token;

                    WebRequest tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");

                    tRequest.Method = "post";

                    tRequest.ContentType = "application/json";

                    var data = new
                    {                        
                        notification = new
                        {
                            title = tagMsg,
                            body = message                   

                            //icon = "myicon"
                        },
                        to = deviceId
                    };

                    var serializer = new JavaScriptSerializer();

                    var json = serializer.Serialize(data);

                    Byte[] byteArray = Encoding.UTF8.GetBytes(json);

                    tRequest.Headers.Add(string.Format("Authorization: key={0}", applicationID));

                    tRequest.Headers.Add(string.Format("Sender: id={0}", senderId));

                    tRequest.ContentLength = byteArray.Length;


                    using (Stream dataStream = tRequest.GetRequestStream())
                    {

                        dataStream.Write(byteArray, 0, byteArray.Length);


                        using (WebResponse tResponse = tRequest.GetResponse())
                        {

                            using (Stream dataStreamResponse = tResponse.GetResponseStream())
                            {

                                using (StreamReader tReader = new StreamReader(dataStreamResponse))
                                {

                                    string sResponseFromServer = tReader.ReadToEnd();

                                    string str = sResponseFromServer;

                                }
                            }
                        }
                    }
                }
                
            }
            catch (Exception ex)
            {
                string str = ex.Message;
            }
        }

        public static async Task<bool> SendPushNotification(string[] deviceTokens, string title, string body, object data)
        {
            bool sent = true;
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("url", "");
            dict.Add("dl", "");

            if (deviceTokens.Count() > 0)
            {
                for (int i = 0; i < deviceTokens.Length; i++)
                {
                    var messageInformation = new Message()
                    {
                        notification = new Notification()
                        {
                            title = title,
                            body = body
                            //mutable_content = true,
                            //sound = "Tri-tone"
                        },

                        data = dict,
                        to = deviceTokens[i]
                        //registration_ids = deviceTokens
                    };

                    string jsonMessage = JsonConvert.SerializeObject(messageInformation);

                    var request = new HttpRequestMessage(HttpMethod.Post, FireBasePushNotificationURL);
                    request.Headers.TryAddWithoutValidation("Sender", $"id={SenderId}");
                    request.Headers.TryAddWithoutValidation("Authorization", "key=" + ServerKey);
                    request.Content = new StringContent(jsonMessage, Encoding.UTF8, "application/json");

                    HttpResponseMessage result;
                    using (var client = new HttpClient())
                    {
                        result = await client.SendAsync(request);
                        sent = sent && result.IsSuccessStatusCode;
                    }
                }


            }
            return sent;
        }

    }
}
