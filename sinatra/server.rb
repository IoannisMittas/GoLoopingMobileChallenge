require 'sinatra'
require 'json'

set :bind, '0.0.0.0'

get '/' do
  "Hello, Get!"
end

post '/' do
  "Hello, Post!"
end

post '/session/new' do
  
  request.body.rewind
  request_json = JSON.parse(request.body.read)
  
  if request_json["email"] != "user@email.com" || request_json["password"] != "password"
    return status 403
  else
    content_type :json
    { :user_id => "1",
      :token => "abcdef" }.to_json
  end
      
end

delete '/session' do

  if request.env['HTTP_BEARER'] != 'abcdef'
    return status 403
  else
    content_type :json
    { :user_id => "1234" }.to_json
  end
  
end

get '/user/:userid' do
  if request.env['HTTP_BEARER'] != 'abcdef'
    return status 403
  else
    content_type :json
    { :email => "foo@bar.com", :avatar_url => "http://#{request.env['HTTP_HOST']}/avatar.png" }.to_json
  end
  
end

post '/user/:user_id/avatar' do
  if request.env['HTTP_BEARER'] != 'abcdef'
    return status 403
  else
    content_type :json
    { :avatar_url => "http://#{request.env['HTTP_HOST']}/avatar.png" }.to_json
  end
end